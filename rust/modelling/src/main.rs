use glutin::event::{Event, WindowEvent};
use glutin::event_loop::ControlFlow;
use glutin::window::WindowBuilder;
use glutin::ContextBuilder;
use std::ffi::CString;

fn main() {
    let el = glutin::event_loop::EventLoop::new();
    let wb = WindowBuilder::new().with_title("Rust OpenGL Triangle").with_inner_size(glutin::dpi::LogicalSize::new(800, 600));
    let windowed_context = ContextBuilder::new()
        .with_vsync(true)
        .build_windowed(wb, &el)
        .unwrap();
    let windowed_context = unsafe { windowed_context.make_current().unwrap() };

    gl::load_with(|s| windowed_context.get_proc_address(s) as *const _);

    let vertices: [f32; 9] = [
        0.0,  0.5, 0.0,  // Top
       -0.5, -0.5, 0.0,  // Bottom left
        0.5, -0.5, 0.0,  // Bottom right
    ];

    let mut vao = 0;
    let mut vbo = 0;
    unsafe {
        gl::GenVertexArrays(1, &mut vao);
        gl::GenBuffers(1, &mut vbo);

        gl::BindVertexArray(vao);

        gl::BindBuffer(gl::ARRAY_BUFFER, vbo);
        gl::BufferData(gl::ARRAY_BUFFER, (vertices.len() * std::mem::size_of::<f32>()) as isize, vertices.as_ptr() as *const _, gl::STATIC_DRAW);

        gl::VertexAttribPointer(0, 3, gl::FLOAT, gl::FALSE, (3 * std::mem::size_of::<f32>()) as i32, std::ptr::null());
        gl::EnableVertexAttribArray(0);
    }

    el.run(move |event, _, control_flow| {
        *control_flow = ControlFlow::Poll;
        match event {
            Event::WindowEvent { event, .. } => match event {
                WindowEvent::CloseRequested => *control_flow = ControlFlow::Exit,
                _ => {}
            },
            Event::MainEventsCleared => {
                unsafe {
                    gl::ClearColor(0.1, 0.1, 0.1, 1.0);
                    gl::Clear(gl::COLOR_BUFFER_BIT);
                    gl::BindVertexArray(vao);
                    gl::DrawArrays(gl::TRIANGLES, 0, 3);
                }
                windowed_context.swap_buffers().unwrap();
            }
            _ => {}
        }
    });
}

