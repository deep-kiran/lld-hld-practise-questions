package designPatterns.adaptor.ex1;

public class AdapterPatternTest {

   /**
    * Adapter pattern works as a bridge between two incompatible interfaces.
    * This type of design pattern comes under structural pattern
    * as this pattern combines the capability of two independent interfaces.
    * @param args
    */
   public static void main(String[] args) {
      AudioPlayerObselete audioPlayer = new AudioPlayerObselete();
      audioPlayer.play("mp3", "beyond the horizon.mp3");
      audioPlayer.play("mp4", "alone.mp4");
      audioPlayer.play("vlc", "far far away.vlc");
      audioPlayer.play("avi", "mind me.avi");
   }
}