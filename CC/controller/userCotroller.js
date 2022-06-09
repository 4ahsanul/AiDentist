const dotenv = require('dotenv');
const User = require('../models/userModel');
const bcryptjs = require('bcryptjs');
const jsonwebtoken = require('jsonwebtoken');
const {check, Result} = require('express-validator')
const emailValidator = require('email-validator');

dotenv.config();

// Register Users
exports.DaftarUser = async (req, res) => {
    try {
        const { name, email, jenis_kelamin, telepon, password } = req.body;

        // User Validation
        if (!password) {
            return res
            .status(400)
            .json({
                messege: "Password tidak boleh kosong"
            })
        }

        if (password.length < 8) {
            return res
            .json({
                messege: "Password harus minimal 8 karakter"
            })
        }

        const userExits = await User.findOne({ email }).exec();
        if (userExits) {
            return res.status(400)
            .json({
                messege: "Email sudah ada"
            })
        }

        if (!name) {
            return res
            .status(400)
            .json({
                messege: "Nama tidak boleh kosong"
            })
        }


        if (!email) {
            return res
            .status(404)
            .json({
                messege: "Email tidak boleh kosong"
            })
        }

        if (!emailValidator.validate(email)){
            return res
            .status(404)
            .json({
                messege: "Harus menggunakan simbol '@' pada Email"
            })
        }

        if (!jenis_kelamin) {
            return res
            .status(404)
            .json({
                messege: "Jenis kelamin tidak boleh kosong"
            })
        }

        if (!telepon) {
            return res
            .status(400)
            .json({
                messege: "Nomor telepon tidak boleh kosong"
            })
        }

        // const valTelepon = check('telepon').isNumeric;
        // if (valTelepon) {
        //     return res
        //     .status(400)
        //     .json({
        //         messege: "Nomor anda tidak valid"
        //     })
        // }

        //Hash Password
        const haasPassword = await bcryptjs.hash(password, 10);

        //User Create
        const newUser = new User({
            name: name,
            email: email,
            jenis_kelamin: jenis_kelamin,
            telepon: telepon,
            password: haasPassword
        });

        const user = await newUser.save();
        return res.status(201).json({
            messege: 'User Create',
            data: user
        });
    } catch (error) {
        console.log(error);
    }
} 

// Login User
exports.LoginUser = async (req, res) => {
    const { email, password } = req.body;

    //Validation
    if(!email) {
        return res
        .status(404)
        .json({
            messege: "Email tidak boleh kosong"
        })
    }

    if (!password) {
        return res
        .status(404)
        .json({
            messege: "Password tidak boleh kosong"
        })
    }

    if (!emailValidator.validate(email)){
        return res
        .status(404)
        .json({
            messege: "Harus memakai simbol '@' pada email"
        })
    }

    const emailLogin = await User.findOne({ email: email });
    if (emailLogin) {
        //Proces success in email
       const passwordLogin = await bcryptjs.compare(password, emailLogin.password)
       if(passwordLogin) {
           //Proces success in password
           const data = {
               id: emailLogin._id
           };
           
           const token = jsonwebtoken.sign(data, process.env.JWT_SECRET)
           // send token in cookie
           res.cookie("token", token, {
               httpOnly: true,
            });
           
            return res.status(200).json({
                messege: 'berhasil',
                token: token
            });

            

       } else {
           return res.status(400).json({
               messege: 'Password salah'
           })
       }

    } else {
        return res.status(400).json({
            messege: 'Username tidak tersedia'
        })
    }

    
}