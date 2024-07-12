import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;
import java.util.Random;

/**
 * Created by michael.nyika on 25/08/15.
 */
public class Music implements Runnable{


    public Music(){

    }

    public void run()  {
      playSound();
    }


    private void playSound(){
        int channel = 4; // 0 is a piano, 9 is percussion, other channels are for other instruments
        int volume = 80; // between 0 et 127
        int sleepDurationAfterAllNotesOn = 3000; //originally 3000
        int sleepDurationAfterAllNotesOff = 10; //originally 500

        while(true) {
            try {

                Synthesizer synth = MidiSystem.getSynthesizer();
                synth.open();
                MidiChannel[] channels = synth.getChannels();
                channels[channel].noteOn(getRandomNoteToPlay(127, 1), volume);
                channels[channel].noteOn(getRandomNoteToPlay(127, 1), volume);
                channels[channel].noteOn(getRandomNoteToPlay(127, 1), volume);
                channels[channel].noteOn(getRandomNoteToPlay(127, 1), volume);
                channels[channel].noteOn(getRandomNoteToPlay(127, 1), volume);
                channels[channel].noteOn(getRandomNoteToPlay(127, 1), volume);
                channels[channel].noteOn(getRandomNoteToPlay(127, 1), volume);
                channels[channel].noteOn(getRandomNoteToPlay(127, 1), volume);
                channels[channel].noteOn(getRandomNoteToPlay(127, 1), volume);
                channels[channel].noteOn(getRandomNoteToPlay(127, 1), volume);
                Thread.sleep(getRandomTimeToWait(sleepDurationAfterAllNotesOn,100));
                channels[channel].allNotesOff();
                Thread.sleep(sleepDurationAfterAllNotesOff);
                synth.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }



    private int getRandomNoteToPlay(int max, int min){
        return new Random().nextInt((max - min) + 1) + min;
    }


    private int getRandomTimeToWait(int max, int min){
        return new Random().nextInt((max - min) + 1) + min;
    }


}
