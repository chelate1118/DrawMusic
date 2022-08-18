mod calculator;
mod sound;

use std::thread;
use sound::Sound;

fn input() -> String {
    let mut tmp : String = String::new();
    std::io::stdin().read_line(&mut tmp).ok();

    tmp.trim().to_string()
}

fn main() {
    loop {
        match input().trim() {
            "c s" => {
                calculator::sum(input())
            },

            "c m" => {
                calculator::multiple(input())
            },

            "s p" => {
                Sound::play(input());
            },

            "t" => { break; },
            _ => { println!("error") }
        }
    }
}
