const mongoose = require('mongoose');

const contactSchema = mongoose.Schema({
  name: { type: String, required: true },
  email: { type: String },
  phone: { type: String, required: true },
  socialMedia: { type: String },
  group: { type: String, required: true },
  country: { type: String },
  carrier: { type: String },
  lineType: { type: String },
}, { timestamps: true });

const Contact = mongoose.model('Contact', contactSchema);
module.exports = Contact;
