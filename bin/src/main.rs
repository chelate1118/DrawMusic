mod calculator;
mod interact;
use std::thread;

fn main() {
    loop {
        match interact::input().trim() {
            "c s" => {
                calculator::sum(interact::input())
            },

            "c m" => {
                calculator::multiple(interact::input())
            },

            "t" => { break; }
            _ => { println!("error") }
        }
    }
}
