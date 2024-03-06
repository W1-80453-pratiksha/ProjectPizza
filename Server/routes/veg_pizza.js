const express = require('express')
const router = express.Router()
const db = require('../db')
const utils = require('../utils')


router.get('/', async (request, response) => {
  try {
    const statement = `
            SELECT Id, p_name, details, image, price,category
            FROM pizza
        `
    const [result] = await db.execute(statement, [])
    response.send(utils.createSuccess(result))
  } catch (ex) {
    response.send(utils.createError(ex))
  }
})


router.get('/non_veg', async (request, response) => {
  try {
    const statement = `
            SELECT Id, p_name, details, image, price
            FROM pizza Where category='non_veg'
        `
    const [result] = await db.execute(statement, [])
    response.send(utils.createSuccess(result))
  } catch (ex) {
    response.send(utils.createError(ex))
  }
})
router.get('/veg', async (request, response) => {
  try {
    const statement = `
            SELECT Id, p_name, details, image, price
            FROM pizza Where category='veg'
        `
    const [result] = await db.execute(statement, [])
    response.send(utils.createSuccess(result))
  } catch (ex) {
    response.send(utils.createError(ex))
  }
})

router.get('/:Id', async (request, response) => {
  const Id=request.params;
  try {
    const statement = `
            SELECT Id, p_name, details, image, price
            FROM pizza Where Id='?'
        `
    const [result] = await db.execute(statement, [Id])
    response.send(utils.createSuccess(result))
  } catch (ex) {
    response.send(utils.createError(ex))
  }
})

module.exports = router