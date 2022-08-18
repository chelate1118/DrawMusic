
use std::fs::File;
use std::io::BufReader;
use rodio::{Decoder, OutputStream, source::Source};

pub(crate) struct Sound {

}

impl Sound {
    const PATH : &'static str = "src/main/resources/com/draw/drawmusic/sound_rust/";

    pub(crate) fn play(file_name: String)
    {
        let (_stream, stream_handle) = OutputStream::try_default().unwrap();

        println!("{}", format!("{}{}", Sound::PATH, file_name));

        let file = BufReader::new(File::open(format!("{}{}", Sound::PATH, file_name)).unwrap());
        let source = Decoder::new(file).unwrap();
        stream_handle.play_raw(source.convert_samples()).ok();

        std::thread::sleep(std::time::Duration::from_millis(700));
    }
}
