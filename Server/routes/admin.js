const express = require('express')
const router = express.Router()
const db = require('../db')
const utils = require('../utils')
const cryptoJs = require('crypto-js')
const jwt = require('jsonwebtoken')
const config = require('../config')

router.post('/signup', async (request, response) => {
  const { firstName, lastName, email, password } = request.body

  try {
    const encryptedPassword = String(cryptoJs.SHA256(password))
    const statement = `
        insert into admin 
            (firstName, lastName, email, password) 
        values
            (?, ?, ?, ?)`
    const result = await db.execute(statement, [
      firstName,
      lastName,
      email,
      encryptedPassword,
    ])
    response.send(utils.createSuccess(result))
  } catch (ex) {
    response.send(utils.createError(ex))
  }
})

router.post('/signin', async (request, response) => {
  const { email, password } = request.body
  try {
    const encryptedPassword = String(cryptoJs.SHA256(password))
    console.log(encryptedPassword)
    const statement = `
            select adminId, firstName, lastName 
            from admin
            where 
                email = ? and password = ?`

    const [admins] = await db.execute(statement, [email, encryptedPassword])
    if (admins.length == 0) {
      response.send(utils.createError('invalid admin name or password'))
    } else {
      const admin = admins[0]
      const token = jwt.sign(
        {
          id: admin['adminId'],
          firstName: admin['firstName'],
        },
        config.secret
      )
      response.send(
        utils.createSuccess({
          token,
          firstName: admin['firstName'],
          lastName: admin['lastName'],
        })
      )
    }
  } catch (ex) {
    response.send(utils.createError(ex))
  }
})

module.exports = router
