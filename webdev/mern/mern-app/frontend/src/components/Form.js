import React, { useState } from 'react';
import axios from 'axios';
import { TextField, Button, Container } from '@material-ui/core';

const Form = () => {
  const [formData, setFormData] = useState({ name: '', email: '', phone: '', group: '' });

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post(process.env.REACT_APP_BACKEND_URL, formData);
      console.log('Form submitted successfully:', response.data);
    } catch (error) {
      console.error('Error submitting form:', error);
    }
  };

  return (
    <Container>
      <form onSubmit={handleSubmit}>
        <TextField name="name" label="Name" value={formData.name} onChange={handleChange} required />
        <TextField name="email" label="Email" value={formData.email} onChange={handleChange} required />
        <TextField name="phone" label="Phone" value={formData.phone} onChange={handleChange} required />
        <TextField name="group" label="Group" value={formData.group} onChange={handleChange} required />
        <Button type="submit" variant="contained" color="primary">Submit</Button>
      </form>
    </Container>
  );
};

export default Form;
