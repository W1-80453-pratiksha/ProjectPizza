const express = require('express')
const router = express.Router()
const db = require('../db')
const utils = require('../utils')



router.get('/:userId', async (request, response) => {
  console.log(request.params)
  const { userId } = request.params;
 
  try {
    const statement = `
              SELECT 
                  pizza.p_name,pizza.price,orderDetails.id, orderDetails.quantity, orderDetails.amount, orderDetails.createdTimestamp
             FROM orderDetails, pizza
              WHERE 
                  userId = ? AND pizza.Id = orderDetails.p_Id 
          `
    const [details] = await db.execute(statement, [userId])
    console.log(details)
    response.send(utils.createSuccess(details))
  } catch (ex) {
    response.send(utils.createError(ex))
  }
})

router.post('/:userId', async (request, response) => {
  const {p_Id,quantity,amount  } = request.body
  const {userId}=request.params;
 try{
      const statementOrderDetails = `
        insert into orderDetails 
            (userId,p_Id,quantity,amount)
        values
            (?,?,?,?)`

      const result=await db.execute(statementOrderDetails, [
        userId,
        p_Id,
        quantity,
        amount
      ])
      response.send(utils.createSuccess(result));
    }
   catch (ex) {
    console.log(ex)
    response.send(utils.createError(ex))
  }
})

module.exports = router