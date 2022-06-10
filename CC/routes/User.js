const express = require("express");
const router = express.Router();
const { DaftarUser, LoginUser, profileUser } = require('../controller/userCotroller');
router.post('/daftar', DaftarUser);
router.post('/login', LoginUser);
// router.get('/profile', profileUser);

module.exports = router;