const express = require("express");
const routes = require("./routes/note.routes");

const app = express();

const errorHandler = require("./middlewares/error.middleware");

// Error handler (must be last)
app.use(errorHandler);




// Middleware to parse JSON
app.use(express.json());
app.use("/api", routes);

// Health check route
app.get("/", (req, res) => {
  res.json({
    success: true,
    message: "Notes API is running"
  });
});

module.exports = app;
