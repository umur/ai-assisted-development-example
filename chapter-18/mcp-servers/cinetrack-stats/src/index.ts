import { Server } from "@modelcontextprotocol/sdk/server/index.js";
import { StdioServerTransport } from "@modelcontextprotocol/sdk/server/stdio.js";
import {
  CallToolRequestSchema,
  ListToolsRequestSchema,
} from "@modelcontextprotocol/sdk/types.js";
import pg from "pg";

const { Pool } = pg;

const pool = new Pool({
  connectionString:
    process.env.DATABASE_URL ??
    "postgresql://cinetrack:cinetrack@localhost:5432/cinetrack",
});

const server = new Server(
  { name: "cinetrack-stats", version: "1.0.0" },
  { capabilities: { tools: {} } }
);

server.setRequestHandler(ListToolsRequestSchema, async () => ({
  tools: [
    {
      name: "get_table_stats",
      description:
        "Returns row counts for all tables in the CinéTrack database. " +
        "Use this when asked about data volume or table size.",
      inputSchema: { type: "object", properties: {} },
    },
    {
      name: "get_recent_reviews",
      description:
        "Returns the most recent reviews with movie title and author username. " +
        "Use this when asked about recent activity or review content.",
      inputSchema: {
        type: "object",
        properties: {
          limit: {
            type: "number",
            description: "Number of reviews to return. Defaults to 10.",
          },
        },
      },
    },
  ],
}));

server.setRequestHandler(CallToolRequestSchema, async (request) => {
  if (request.params.name === "get_table_stats") {
    const result = await pool.query(`
      SELECT relname AS table_name, n_live_tup AS row_count
      FROM pg_stat_user_tables
      ORDER BY n_live_tup DESC
    `);
    return {
      content: [{ type: "text", text: JSON.stringify(result.rows, null, 2) }],
    };
  }

  if (request.params.name === "get_recent_reviews") {
    const limit =
      (request.params.arguments as { limit?: number })?.limit ?? 10;
    const result = await pool.query(
      `SELECT r.id, r.rating, r.comment, u.username, m.title
       FROM reviews r
       JOIN app_users u ON u.id = r.author_id
       JOIN movies m ON m.id = r.movie_id
       ORDER BY r.id DESC
       LIMIT $1`,
      [limit]
    );
    return {
      content: [{ type: "text", text: JSON.stringify(result.rows, null, 2) }],
    };
  }

  throw new Error(`Unknown tool: ${request.params.name}`);
});

const transport = new StdioServerTransport();
await server.connect(transport);
