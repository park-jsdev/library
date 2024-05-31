const express = require('express');
const mongoose = require('mongoose');
const dotenv = require('dotenv');

dotenv.config();

const app = express();
app.use(express.json());

mongoose.connect(process.env.MONGO_URI, {
  useNewUrlParser: true,
  useUnifiedTopology: true,
});

const formSchema = new mongoose.Schema({
  name: String,
  email: String,
  phone: String,
  isValid: Boolean,
});

const Form = mongoose.model('Form', formSchema);

app.post('/api/forms', async (req, res) => {
  const { name, email, phone } = req.body;
  const newForm = new Form({ name, email, phone, isValid: true });
  await newForm.save();
  res.status(201).send(newForm);
});

app.get('/api/forms', async (req, res) => {
  const forms = await Form.find();
  res.status(200).send(forms);
});

const PORT = process.env.PORT || 5000;
app.listen(PORT, () => console.log(`Server running on port ${PORT}`));
