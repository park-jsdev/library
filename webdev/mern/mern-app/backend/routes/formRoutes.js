const express = require('express');
const router = express.Router();
const { createForm, updateForm, getFormById, getAllForms, deleteFormById } = require('../controllers/formController');

router.route('/').post(createForm).get(getAllForms);
router.route('/:id').get(getFormById).put(updateForm).delete(deleteFormById);

module.exports = router;
