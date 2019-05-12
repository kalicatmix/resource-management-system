//store data simply
module.exports = {
    _sessions: {},
    addSession(key, data) {
        this._sessions[key] = data
    },
    delSession(key) {
        this._sessions[key] = null
    },
    getSession(key) {
        return this._sessions[key]
    }
}