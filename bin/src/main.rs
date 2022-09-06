use sound::Sound;
use interact::input;
use crate::interact::logging;
use chrono;

mod sound;
mod calculator;
mod interact;

fn main() {
    Sound::reset();
    logging("start", format!("{}", chrono::offset::Local::now()));

    loop {
        match input().trim() {
            "s p" => {
                Sound::play(input());
            }

            "s g" => {
                Sound::generate(input())
            }

            "t" => { break; }
            _ => { println!("error") }
        }
    }
}
