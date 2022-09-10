mod controller;
mod calculator;

use jni::sys::{jclass, jint, jintArray};
use jni::JNIEnv;
use controller::init;

const ARRAY_SIZ: usize = 100;

#[no_mangle]
pub extern fn Java_com_draw_drawmusic_rust_Rust_init(_env: JNIEnv, _class: jclass) {
    init();
}

#[no_mangle]
pub extern fn Java_com_draw_drawmusic_rust_Rust_xx(env: JNIEnv, _class: jclass, arr: jintArray) -> jint {
    println!("called!");
    let x = &mut [0; ARRAY_SIZ];
    env.get_int_array_region(arr, 0, x).unwrap();

    let mut ret = 0;

    for i in 0..env.get_array_length(arr).unwrap() {
        ret += x[i as usize];
    }

    ret
}
