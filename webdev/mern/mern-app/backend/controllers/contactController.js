const axios = require('axios');
const Contact = require('../models/Contact');
const { formatPhoneNumberForStorage } = require('../utils/format');

const validatePhoneNumber = async (phone) => {
  try {
    const cleanedPhone = formatPhoneNumberForStorage(phone); // Clean the phone number before validation
    const response = await axios.get(`http://apilayer.net/api/validate?access_key=${process.env.NUMVERIFY_API_KEY}&number=${cleanedPhone}`);
    return response.data;
  } catch (error) {
    console.error('Error validating phone number:', error);
    return null;
  }
};

const createContact = async (req, res) => {
  const { name, email, phone, socialMedia, group = 'Unknown' } = req.body;
  const validationResponse = await validatePhoneNumber(phone);

  if (validationResponse && validationResponse.valid) {
    const newContact = new Contact({
      name,
      email,
      phone: formatPhoneNumberForStorage(validationResponse.international_format), // Store phone number without spaces
      socialMedia,
      group: group.trim() === '' ? 'Unknown' : group.trim(), // Default group to "Unknown" if empty
      country: validationResponse.country_name,
      carrier: validationResponse.carrier,
      lineType: validationResponse.line_type,
    });
    try {
      const savedContact = await newContact.save();
      res.status(201).json(savedContact);
    } catch (error) {
      res.status(400).json({ error: error.message });
    }
  } else {
    res.status(400).json({ error: 'Invalid phone number' });
  }
};

const getContactById = async (req, res) => {
  try {
    const contact = await Contact.findById(req.params.id);
    if (contact) {
      res.json(contact);
    } else {
      res.status(404).json({ message: 'Contact not found' });
    }
  } catch (error) {
    res.status(400).json({ error: error.message });
  }
};

const getAllContacts = async (req, res) => {
  try {
    const contacts = await Contact.find({});
    res.json(contacts);
  } catch (error) {
    res.status(400).json({ error: error.message });
  }
};

const deleteContactById = async (req, res) => {
  try {
    const contact = await Contact.findByIdAndDelete(req.params.id);
    if (contact) {
      res.json({ message: 'Contact deleted successfully' });
    } else {
      res.status(404).json({ message: 'Contact not found' });
    }
  } catch (error) {
    res.status(400).json({ error: error.message });
  }
};

const updateContactById = async (req, res) => {
  const { name, email, socialMedia, group } = req.body;
  try {
    const updatedContact = await Contact.findByIdAndUpdate(
      req.params.id,
      { name, email, socialMedia, group },
      { new: true, runValidators: true }
    );
    if (updatedContact) {
      res.json(updatedContact);
    } else {
      res.status(404).json({ message: 'Contact not found' });
    }
  } catch (error) {
    res.status(400).json({ error: error.message });
  }
};

module.exports = { createContact, getContactById, getAllContacts, deleteContactById, updateContactById };
