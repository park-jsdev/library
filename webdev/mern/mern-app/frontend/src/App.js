import React, { useState, useEffect } from 'react';
import axios from 'axios';

function App() {
  const [forms, setForms] = useState([]);
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');
  const [phone, setPhone] = useState('');

  useEffect(() => {
    axios.get('http://localhost:5000/api/forms')
      .then(response => {
        setForms(response.data);
      });
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post('http://localhost:5000/api/forms', { name, email, phone });
      setForms([...forms, response.data]);
      setName('');
      setEmail('');
      setPhone('');
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div className="App">
      <h1>Form Submissions</h1>
      <form onSubmit={handleSubmit}>
        <input type="text" value={name} onChange={(e) => setName(e.target.value)} placeholder="Name" required />
        <input type="email" value={email} onChange={(e) => setEmail(e.target.value)} placeholder="Email" required />
        <input type="text" value={phone} onChange={(e) => setPhone(e.target.value)} placeholder="Phone" required />
        <button type="submit">Submit</button>
      </form>
      <ul>
        {forms.map(form => (
          <li key={form._id}>
            {form.name} ({form.email}) - Phone Valid: {form.isValid ? 'Yes' : 'No'}
          </li>
        ))}
      </ul>
    </div>
  );
}

export default App;
