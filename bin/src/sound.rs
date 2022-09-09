#![allow(unused)]

use std::thread;
use std::fs::File;
use std::fs;
use std::io::BufReader;
use hound;
use rodio::{Decoder, OutputStream, source::Source};
use std::f32::consts::PI;
use std::fmt::Debug;
use std::str::FromStr;
use crate::calculator;
use crate::interact::{split_two_commands_whitespace};

pub(crate) struct Sound {
    // amp: calculator::Spline
}

impl Sound {
    const PATH: &'static str = "src/main/resources/com/draw/drawmusic/sound_rust/";

    pub(crate) fn generate(command: String) {
        let (file_name, frequency) = split_two_commands_whitespace::<String, f64>(command);
        let file_name: String = format!("{}{}", Sound::PATH, file_name);
        let spec = hound::WavSpec {
            channels: 1,
            sample_rate: 44100,
            bits_per_sample: 16,
            sample_format: hound::SampleFormat::Int,
        };

        let mut writer = hound::WavWriter::create(file_name, spec).unwrap();
        for i in (0..44100).map(|x| x as f32 / 44100.) {
            writer.write_sample(((i * 440. * 2. * PI).sin() * 1000.) as i16).unwrap();
        }
    }

    pub(crate) fn play(command: String) {
        let(file_name, millis) = split_two_commands_whitespace::<String, u64>(command);
        Sound::play_with_time(file_name, millis)
    }

    pub(crate) fn play_with_time(file_name: String, millis: u64) {
        let (_stream, stream_handle) = OutputStream::try_default().unwrap();

        let file = BufReader::new(File::open(format!("{}{}", Sound::PATH, file_name)).unwrap());
        let source = Decoder::new(file).unwrap();
        stream_handle.play_raw(source.convert_samples()).ok();

        thread::sleep(std::time::Duration::from_millis(millis));
    }


    pub(crate) fn reset() {
        let paths = fs::read_dir(Sound::PATH).unwrap();

        for path in paths {
            fs::remove_file(path.unwrap().path().to_str().unwrap());
        }
    }
}
