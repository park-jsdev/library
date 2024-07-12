const mongoose = require('mongoose');

const formSchema = mongoose.Schema({
  name: { type: String, required: true },
  email: { type: String, required: true },
  phone: { type: String, required: true },
  socialMedia: { type: String },
  group: { type: String },
  country: { type: String },
  carrier: { type: String },
  lineType: { type: String },
}, { timestamps: true });

const Form = mongoose.model('Form', formSchema);
module.exports = Form;
