use std::path::PathBuf;
use anyhow::{anyhow, Context};
use clap::Parser;
use ffmpeg_next::{codec, format, media};

#[derive(Debug, Parser)]
struct Args {
    #[arg(short, long)]
    pub input: PathBuf,
}

fn main() -> anyhow::Result<()> {
    let Args { input } = Args::parse();

    println!("Loading {:?}...", &input);

    let input = format::input(&input).context("Opening input")?;
    println!("Format: {}", input.format().description());
    let audio_stream = input.streams().best(media::Type::Audio).ok_or(anyhow!("No audio stream"))?;
    let audio_param = audio_stream.parameters();
    let audio_ctx = codec::Context::from_parameters(audio_param).context("Getting codec context")?;
    let audio_codec = codec::decoder::find(audio_ctx.id()).ok_or(anyhow!("Unable to find audio codec"))?;
    println!("Codec: {}", audio_codec.description());

    Ok(())
}
