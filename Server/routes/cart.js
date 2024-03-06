const express = require('express')
const router = express.Router()
const db = require('../db')
const utils = require('../utils')

router.get('/cart', async (request, response) => {
  try {
    const statement = `
            SELECT id, totalAmount, 
            FROM cart
            WHERE
                userId = ?
        `
    const [orders] = await db.execute(statement, [request.data.id])
    response.send(utils.createSuccess(orders))
  } catch (ex) {
    response.send(utils.createError(ex))
  }
})

router.get('/details/:userId', async (request, response) => {
  console.log(request.params)
  const { userId } = request.params;
 
  try {
    const statement = `
              SELECT 
                  pizza.p_name,pizza.details, pizza.price, pizza.Id,pizza.image, cart.quantity, cart.amount, cart.createdTimestamp
              FROM cart, pizza
              WHERE 
                  cart.userId = ? AND pizza.Id = cart.p_Id 
          `
    const [details] = await db.execute(statement, [userId])
    console.log(details)
    response.send(utils.createSuccess(details))
  } catch (ex) {
    console.log(ex);
    response.send(utils.createError(ex))
  }
})

router.post('/cart/:userId', async (request, response) => {
  const { id,userId,quantity,price, amount, } = request.body
  const userId1=request.params;
  console.log(userId);
  try {
    // step1: create an order
    const statementOrder = `
        insert into cart 
            (userId,p_Id,quantity,price,amount)
        values
            (?, ?,?,?,?)`
    const cart = await db.execute(statementOrder, [
      userId,id,quantity,price,amount
    ])

    
    
    response.send(utils.createSuccess(cart))
  } catch (ex) {
    console.log(ex)
    response.send(utils.createError(ex))
  }
})
router.delete("/cart/:p_id", async (request, response) => {
  const p_id = [request.params.p_id]; // Convert p_id to an array
  try {
    const statement = "DELETE FROM cart WHERE p_Id=?";
    const cart = await db.execute(statement, p_id);
    console.log(cart);
    response.send(utils.createSuccess(cart));
  } catch (ex) {
    console.log(ex);
    response.send(utils.createError(ex));
  }
});


module.exports = router