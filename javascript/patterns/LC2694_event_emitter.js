class EventEmitter {
    constructor(){
        this.list = {};
    }
    
    /**
     * @param {string} eventName
     * @param {Function} callback
     * @return {Object}
     */
    subscribe(eventName, callback) {
        if (eventName in this.list){
            this.list[eventName].push(callback);
        } else {
            this.list[eventName] = [callback];
        }

        return {
            unsubscribe: () => {
                for (let i=0;i<this.list[eventName].length; i++){
                    if (this.list[eventName][i] == callback){
                        this.list[eventName].splice(i, 1);
                        return;
                    }
                }
            }
        };
    }
    
    /**
     * @param {string} eventName
     * @param {Array} args
     * @return {Array}
     */
    emit(eventName, args = []) {
        let res = [];
        if (this.list[eventName] === undefined) return res;
        for (let i=0;i<this.list[eventName].length;i++){
            res.push(this.list[eventName][i](...args));
        }
        return res;
    }
}

/**
 * const emitter = new EventEmitter();
 *
 * // Subscribe to the onClick event with onClickCallback
 * function onClickCallback() { return 99 }
 * const sub = emitter.subscribe('onClick', onClickCallback);
 *
 * emitter.emit('onClick'); // [99]
 * sub.unsubscribe(); // undefined
 * emitter.emit('onClick'); // []
 */