const mongoose = require('mongoose');

const userSchema = new mongoose.Schema({
    name: {
        type: String
    },
    email: {
        type: String
    },
    jenis_kelamin: {
        type: String
    },
    telepon: {
        type: String
    },
    password: {
        type: String
    }
})

module.exports = mongoose.model('User', userSchema);