import React, { useState } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Container from '@material-ui/core/Container';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import Snackbar from '@material-ui/core/Snackbar';
import Alert from '@material-ui/lab/Alert';
import axios from 'axios';

const useStyles = makeStyles((theme) => ({
  formContainer: {
    marginTop: theme.spacing(4),
  },
  formItem: {
    marginBottom: theme.spacing(2),
  },
}));

const FormPage = () => {
  const classes = useStyles();
  const [formData, setFormData] = useState({ name: '', email: '', phone: '', group: '' });
  const [snackbar, setSnackbar] = useState({ open: false, severity: 'success', message: '' });

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await axios.post('http://localhost:5000/api/forms', formData);
      setSnackbar({ open: true, severity: 'success', message: 'Form submitted successfully' });
      setFormData({ name: '', email: '', phone: '', group: '' });
    } catch (error) {
      setSnackbar({ open: true, severity: 'error', message: 'Form submission failed' });
    }
  };

  const handleSnackbarClose = () => {
    setSnackbar({ ...snackbar, open: false });
  };

  return (
    <Container maxWidth="sm" className={classes.formContainer}>
      <form onSubmit={handleSubmit}>
        <TextField
          fullWidth
          className={classes.formItem}
          label="Name"
          name="name"
          value={formData.name}
          onChange={handleChange}
          required
        />
        <TextField
          fullWidth
          className={classes.formItem}
          label="Email"
          name="email"
          value={formData.email}
          onChange={handleChange}
          required
        />
        <TextField
          fullWidth
          className={classes.formItem}
          label="Phone"
          name="phone"
          value={formData.phone}
          onChange={handleChange}
          required
        />
        <TextField
          fullWidth
          className={classes.formItem}
          label="Group"
          name="group"
          value={formData.group}
          onChange={handleChange}
          required
        />
        <Button variant="contained" color="primary" type="submit">
          Submit
        </Button>
      </form>
      <Snackbar open={snackbar.open} autoHideDuration={6000} onClose={handleSnackbarClose}>
        <Alert onClose={handleSnackbarClose} severity={snackbar.severity}>
          {snackbar.message}
        </Alert>
      </Snackbar>
    </Container>
  );
};

export default FormPage;
