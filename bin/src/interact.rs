
use std::fmt::Debug;
use std::str::FromStr;

pub(crate) fn split_two_commands_whitespace<Type1: FromStr, Type2: FromStr>(command: String) -> (Type1, Type2)
    where <Type1 as FromStr>::Err: Debug, <Type2 as FromStr>::Err: Debug
{
    let tmp: Option<(&str, &str)> = command.split_once(' ');
    (FromStr::from_str(tmp.unwrap().0).unwrap(), FromStr::from_str(tmp.unwrap().1).unwrap())
}

pub(crate) fn input() -> String {
    let mut tmp: String = String::new();
    std::io::stdin().read_line(&mut tmp).ok();

    tmp.trim().to_string()
}
