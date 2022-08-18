#![allow(unused_imports)]
#![allow(unused_variables)]
#![allow(unused_assignments)]
#![allow(dead_code)]

use std::thread;
use std::fs::File;
use std::io::BufReader;
use rodio::{Decoder, OutputStream, source::Source};

pub(crate) struct Sound {

}

impl Sound {
    const PATH : &'static str = "src/main/resources/com/draw/drawmusic/sound_rust/";

    pub(crate) fn generate(command: String) {
        let file_name : String;
        let frequency : f64;

        {
            let tmp : Option<(&str, &str)> = command.split_once(' ');
            file_name = tmp.unwrap().0.parse().unwrap();
            frequency = tmp.unwrap().1.parse().unwrap();
        }

        let file_name : String = format!("{}{}", Sound::PATH, file_name);

    }

    pub(crate) fn play(file_name: String)
    {
        let (_stream, stream_handle) = OutputStream::try_default().unwrap();

        let file = BufReader::new(File::open(format!("{}{}", Sound::PATH, file_name)).unwrap());
        let source = Decoder::new(file).unwrap();
        stream_handle.play_raw(source.convert_samples()).ok();

        thread::sleep(std::time::Duration::from_millis(700));
    }
}
