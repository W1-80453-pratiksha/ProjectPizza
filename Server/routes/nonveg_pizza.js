const express = require('express')
const router = express.Router()
const db = require('../db')
const utils = require('../utils')

router.get('/', async (request, response) => {
  try {
    const statement = `
            SELECT nv_Id, nv_name, details, image, price
            FROM pizza Where category=="non_veg"
        `
    const [result] = await db.execute(statement, [])
    response.send(utils.createSuccess(result))
  } catch (ex) {
    response.send(utils.createError(ex))
  }
})

module.exports = router