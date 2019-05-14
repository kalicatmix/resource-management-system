module.exports={
    _localStorage:localStorage,
    addToken(token){
    this._localStorage.setItem("token",token)
    },
    delToken(token){
    this._localStorage.clear()
    },
    chgToken(token){
    this.addToken(token)
    },
    getToken(){
        return this._localStorage.getItem("token")
    }
}