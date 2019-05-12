module.exports={
 buildErrorMessage(){
     return {
     status:0xff,
     info:"web error"
     }
 },
 buildMessage(message){
     return {
     status:0x00,
     info:message
     }
 }
}