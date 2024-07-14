const express = require('express');
const {
  createContact,
  getContactById,
  getAllContacts,
  deleteContactById,
  updateContactById
} = require('../controllers/contactController');
const router = express.Router();

router.post('/', createContact);
router.get('/:id', getContactById);
router.get('/', getAllContacts);
router.delete('/:id', deleteContactById);
router.put('/:id', updateContactById);

module.exports = router;
