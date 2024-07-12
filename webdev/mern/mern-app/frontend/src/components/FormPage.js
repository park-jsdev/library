import React, { useState } from 'react';
import { Box, TextField, Button, Typography } from '@mui/material';
import axios from 'axios';

const FormPage = () => {
  const [formData, setFormData] = useState({
    name: '',
    email: '',
    phone: '',
    socialMedia: '',
    group: '',
  });
  const [successMessage, setSuccessMessage] = useState('');
  const [errorMessage, setErrorMessage] = useState('');

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post('http://localhost:5000/api/forms', formData);
      setSuccessMessage('Form submitted successfully!');
      setFormData({
        name: '',
        email: '',
        phone: '',
        socialMedia: '',
        group: '',
      });
      setTimeout(() => {
        setSuccessMessage('');
      }, 3000);
    } catch (error) {
      setErrorMessage('Failed to submit form. Please try again.');
      setTimeout(() => {
        setErrorMessage('');
      }, 3000);
    }
  };

  return (
    <Box className="container">
      <Box className="header">
        <Typography variant="h4">Contact Management Dashboard</Typography>
      </Box>
      <Box className="navbar">
        <a href="/add-new-contact">Add New Contact</a>
        <a href="/view-contacts">View Contacts</a>
      </Box>
      <Box className="form-container">
        <Box className="page-title">
          <Typography variant="h5">Add New Contact</Typography>
        </Box>
        {successMessage && <Box className="success-message">{successMessage}</Box>}
        {errorMessage && <Box className="error-message">{errorMessage}</Box>}
        <form onSubmit={handleSubmit}>
          <TextField
            label="Name"
            name="name"
            value={formData.name}
            onChange={handleChange}
            variant="outlined"
            required
            fullWidth
            margin="normal"
          />
          <TextField
            label="Email"
            name="email"
            value={formData.email}
            onChange={handleChange}
            variant="outlined"
            required
            fullWidth
            margin="normal"
          />
          <TextField
            label="Phone"
            name="phone"
            value={formData.phone}
            onChange={handleChange}
            variant="outlined"
            required
            fullWidth
            margin="normal"
          />
          <TextField
            label="Social Media"
            name="socialMedia"
            value={formData.socialMedia}
            onChange={handleChange}
            variant="outlined"
            fullWidth
            margin="normal"
          />
          <TextField
            label="Group"
            name="group"
            value={formData.group}
            onChange={handleChange}
            variant="outlined"
            fullWidth
            margin="normal"
          />
          <Button variant="contained" color="primary" type="submit">
            Submit
          </Button>
        </form>
      </Box>
    </Box>
  );
};

export default FormPage;
