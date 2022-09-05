use sound::Sound;
use interact::input;

mod sound;
mod calculator;
mod interact;

fn main() {
    Sound::reset();

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
