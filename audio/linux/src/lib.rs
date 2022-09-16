extern crate core;

mod controller;
mod calculator;
mod jni_tools;

use jni::sys::{jclass};
use jni::JNIEnv;
use controller::init;

#[no_mangle]
pub extern fn Java_com_draw_drawmusic_rust_Rust_init(_env: JNIEnv, _class: jclass) {
    init();
}
