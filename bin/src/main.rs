mod sound;

use sound::Sound;

fn input() -> String {
    let mut tmp : String = String::new();
    std::io::stdin().read_line(&mut tmp).ok();

    tmp.trim().to_string()
}

fn main() {
    Sound::init();

    loop {
        match input().trim() {
            "s p" => {
                Sound::play(input());
            },

            "s g" => {
                Sound::generate(input())
            },

            "t" => { break; },
            _ => { println!("error") }
        }
    }
}
