var jwt=require("jsonwebtoken")
module.exports={
  addToken(data,uid){
  return jwt.sign(data,uid,{ expiresIn: 60 * 60 })
  },
  verifyToken(token,uid){
  return new Promise((resolve,reject)=>{
    jwt.verify(token,uid,(err,data)=>{
      if(err)
      reject()
      else
      resolve(data)
    })
  })
  }
}