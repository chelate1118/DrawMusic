
pub(crate) fn input() -> String {
    let mut tmp : String = String::new();
    std::io::stdin().read_line(&mut tmp).ok();

    tmp.trim().to_string()
}
