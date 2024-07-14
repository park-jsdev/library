import React from 'react';
import { Button } from '@mui/material';
import axios from 'axios';

const ContactList = ({ contacts, setContacts }) => {
  const handleDelete = async (id) => {
    try {
      await axios.delete(`http://localhost:5000/api/contacts/${id}`);
      setContacts(contacts.filter(contact => contact._id !== id));
    } catch (error) {
      console.error('Failed to delete contact', error);
    }
  };

  const getSecurityRating = (group) => {
    const trimmedGroup = group.trim().toLowerCase();
    switch (trimmedGroup) {
      case 'good':
        return { rating: 'Good', className: 'good' };
      case 'bad':
        return { rating: 'Bad', className: 'bad' };
      case 'unknown':
      default:
        return { rating: 'Unknown', className: 'unknown' };
    }
  };

  return (
    <div className="table-container">
      <table>
        <thead>
          <tr>
            <th>Name</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Social Media</th>
            <th>Group</th>
            <th>Country</th>
            <th>Carrier</th>
            <th>Line Type</th>
            <th>Security Rating</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {contacts.map((contact) => {
            const { rating, className } = getSecurityRating(contact.group);
            return (
              <tr key={contact._id}>
                <td>{contact.name}</td>
                <td>{contact.email}</td>
                <td>{contact.phone}</td>
                <td>{contact.socialMedia}</td>
                <td>{contact.group}</td>
                <td>{contact.country}</td>
                <td>{contact.carrier}</td>
                <td>{contact.lineType}</td>
                <td className={className}>{rating}</td>
                <td>
                  <Button
                    variant="contained"
                    color="secondary"
                    onClick={() => handleDelete(contact._id)}
                  >
                    Delete
                  </Button>
                </td>
              </tr>
            );
          })}
        </tbody>
      </table>
    </div>
  );
};

export default ContactList;
