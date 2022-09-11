extern crate core;

mod controller;
mod calculator;
mod jni_tools;

use jni::sys::{jclass, jint, jintArray};
use jni::JNIEnv;
use controller::init;

#[no_mangle]
pub extern fn Java_com_draw_drawmusic_rust_Rust_init(_env: JNIEnv, _class: jclass) {
    init();
}

#[no_mangle]
pub extern fn Java_com_draw_drawmusic_rust_Rust_xx(env: JNIEnv, _class: jclass, arr: jintArray) -> jint {
    println!("called!");
    let x = jni_tools::jint_array_to_vec(env, arr);
    let mut ret: i32 = 0;

    for i in 0..env.get_array_length(arr).unwrap() {
        ret += x[i as usize];
    }

    ret
}
