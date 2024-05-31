const express = require('express');
const connectDB = require('./config/db');
const formRoutes = require('./routes/formRoutes');
const dotenv = require('dotenv');

dotenv.config();
connectDB();

const app = express();
app.use(express.json());

app.use('/api/forms', formRoutes);

const PORT = process.env.PORT || 5000;

process.on('unhandledRejection', (err) => {
  console.error('Unhandled Rejection:', err.message);
  process.exit(1);
});

app.listen(PORT, () => console.log(`Server running on port ${PORT}`));
