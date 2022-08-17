
use std::fs::File;
use std::io::BufReader;
use rodio::{Decoder, OutputStream, source::Source};

struct Sound {

}

impl Sound {
    fn play(path : String)
    {
        let (_stream, stream_handle) = OutputStream::try_default().unwrap();

        let file = BufReader::new(File::open(path).unwrap());
        let source = Decoder::new(file).unwrap();
        stream_handle.play_raw(source.convert_samples()).ok();

        std::thread::sleep(std::time::Duration::from_millis(700));
    }
}
