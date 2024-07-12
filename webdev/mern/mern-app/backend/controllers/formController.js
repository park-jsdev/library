const axios = require('axios');
const Form = require('../models/Form');

const validatePhoneNumber = async (phone) => {
  try {
    const response = await axios.get(`http://apilayer.net/api/validate?access_key=${process.env.NUMVERIFY_API_KEY}&number=${phone}`);
    return response.data;
  } catch (error) {
    console.error('Error validating phone number:', error);
    return null;
  }
};

const createForm = async (req, res) => {
  const { name, email, phone, socialMedia, group } = req.body;
  const validationResponse = await validatePhoneNumber(phone);

  if (validationResponse && validationResponse.valid) {
    const newForm = new Form({
      name,
      email,
      phone,
      socialMedia,
      group,
      country: validationResponse.country_name,
      carrier: validationResponse.carrier,
      lineType: validationResponse.line_type,
    });
    try {
      const savedForm = await newForm.save();
      res.status(201).json(savedForm);
    } catch (error) {
      res.status(400).json({ error: error.message });
    }
  } else {
    res.status(400).json({ error: 'Invalid phone number' });
  }
};

const getFormById = async (req, res) => {
  try {
    const form = await Form.findById(req.params.id);
    if (form) {
      res.json(form);
    } else {
      res.status(404).json({ message: 'Form not found' });
    }
  } catch (error) {
    res.status(400).json({ error: error.message });
  }
};

const getAllForms = async (req, res) => {
  try {
    const forms = await Form.find({});
    res.json(forms);
  } catch (error) {
    res.status(400).json({ error: error.message });
  }
};

const deleteFormById = async (req, res) => {
  try {
    const form = await Form.findByIdAndDelete(req.params.id);
    if (form) {
      res.json({ message: 'Form deleted successfully' });
    } else {
      res.status(404).json({ message: 'Form not found' });
    }
  } catch (error) {
    res.status(400).json({ error: error.message });
  }
};

const updateFormById = async (req, res) => {
  const { name, email, socialMedia, group } = req.body;
  try {
    const updatedForm = await Form.findByIdAndUpdate(
      req.params.id,
      { name, email, socialMedia, group },
      { new: true, runValidators: true }
    );
    if (updatedForm) {
      res.json(updatedForm);
    } else {
      res.status(404).json({ message: 'Form not found' });
    }
  } catch (error) {
    res.status(400).json({ error: error.message });
  }
};

module.exports = { createForm, getFormById, getAllForms, deleteFormById, updateFormById };
