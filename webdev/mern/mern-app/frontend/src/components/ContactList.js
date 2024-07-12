import React from 'react';
import { Button } from '@mui/material';

const ContactList = ({ contacts, searchName, filterGroup }) => {
  const handleDelete = async (id) => {
    try {
      await axios.delete(`http://localhost:5000/api/forms/${id}`);
      setContacts(contacts.filter(contact => contact._id !== id));
    } catch (error) {
      console.error('Error deleting contact:', error);
    }
  };

  const filteredContacts = contacts.filter(contact => 
    contact.name.toLowerCase().includes(searchName.toLowerCase()) &&
    contact.group.toLowerCase().includes(filterGroup.toLowerCase())
  );

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
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {filteredContacts.map((contact) => (
            <tr key={contact._id}>
              <td>{contact.name}</td>
              <td>{contact.email}</td>
              <td>{contact.phone}</td>
              <td>{contact.socialMedia}</td>
              <td>{contact.group}</td>
              <td>{contact.country}</td>
              <td>{contact.carrier}</td>
              <td>{contact.lineType}</td>
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
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default ContactList;
