
use std::fmt::Debug;
use std::fs::{File, OpenOptions};
use std::str::FromStr;
use std::io::Write;

pub(crate) fn split_two_commands_whitespace<Type1: FromStr, Type2: FromStr>(command: String) -> (Type1, Type2)
    where <Type1 as FromStr>::Err: Debug, <Type2 as FromStr>::Err: Debug
{
    let tmp: Option<(&str, &str)> = command.split_once(' ');
    (FromStr::from_str(tmp.unwrap().0).unwrap(), FromStr::from_str(tmp.unwrap().1).unwrap())
}

pub(crate) fn input() -> String {
    let mut tmp: String = String::new();
    std::io::stdin().read_line(&mut tmp).ok();

    let ret = tmp.trim().to_string();
    logging("input", ret.clone());

    ret
}

pub(crate) fn logging(log_type: &str, data: String) {
    const PATH: &str = "./bin/src/log";
    let mut file: File = OpenOptions::new()
        .write(true)
        .append(true)
        .open(PATH)
        .unwrap();

    let message = format!("[{}] {}", log_type, data);
    if let Err(e) = writeln!(file, "{}", message) {
        eprintln!("Unable to write file: {}", e);
    };
}
