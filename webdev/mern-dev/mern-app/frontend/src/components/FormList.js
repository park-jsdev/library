import React from 'react';
import axios from 'axios';
import './FormList.css';

const FormList = ({ forms, onDelete }) => {
  const handleDelete = async (id) => {
    try {
      await axios.delete(`http://localhost:5000/api/contacts/${id}`);
      onDelete(id);
    } catch (error) {
      console.error('Error deleting contact:', error);
    }
  };

  return (
    <ul className="form-list">
      {forms.map(form => (
        <li key={form._id} className="form-list-item">
          <div className="form-text">
            <p><strong>{form.name}</strong> ({form.email})</p>
            <p>Phone: {form.phone} - Phone Valid: {form.isValid ? 'Yes' : 'No'}</p>
            <p>Social Media: {form.socialMedia}</p>
            <p>Group: {form.group}</p>
            <p>Country: {form.country}</p>
            <p>Carrier: {form.carrier}</p>
            <p>Line Type: {form.lineType}</p>
            <p>Security Rating: {form.securityRating}</p>
          </div>
          <button className="delete-button" onClick={() => handleDelete(form._id)}>Delete</button>
        </li>
      ))}
    </ul>
  );
};

export default FormList;
