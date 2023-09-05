// Two Stacks solution
// Time: O(1) for each operation
// Space: O(2n) = O(n) 
/**
    The crux is that you track the minimum associated with each elemenet in the stack in a second stack.
 */

class MinStack {
    Stack<Integer> stack;
    Stack<Integer> min_stack;

    public MinStack() {
        this.stack = new Stack<>();
        this.min_stack = new Stack<>();
    }
    
    public void push(int val) {
        stack.push(val);
        // Push the minimum of the current minimum and new value into the min stack
        if (!min_stack.empty()){
            min_stack.push(Math.min(val, min_stack.peek()));
        } else { // if it is empty, simply push the first value
            min_stack.push(val);
        }
    }
    
    public void pop() {
        stack.pop();
        min_stack.pop();
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return min_stack.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */