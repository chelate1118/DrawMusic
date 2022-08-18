#![allow(unused_imports)]
#![allow(unused_variables)]
#![allow(unused_assignments)]
#![allow(dead_code)]

mod calculator;

use std::thread;
use std::fs::File;
use std::io::BufReader;
use hound;
use rodio::{Decoder, OutputStream, source::Source};
use std::f32::consts::PI;

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
        let spec = hound::WavSpec {
            channels : 1,
            sample_rate : 44100,
            bits_per_sample : 16,
            sample_format : hound::SampleFormat::Int
        };

        let mut writer = hound::WavWriter::create(file_name, spec).unwrap();
        for i in (0..44100).map(|x| x as f32 / 44100.) {
            writer.write_sample(((i * 440. * 2. * PI).sin() * 1000.) as i16).unwrap();
        }
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
