package design_patterns.compound_mvc.model;

import java.util.*;
import javax.sound.midi.*;

public class BeatModel implements BeatModelInterface, MetaEventListener {
  Sequencer sequencer;
  ArrayList beatObservers = new ArrayList();
  ArrayList bpmObservers = new ArrayList();
  int bpm = 90;

  Sequence sequence;
  Track track;

  public void initialize() {
    setUpMidi();
    buildTrackAndStart();
  };

  public void on() {
    sequencer.start();
    setBPM(90);
  };

  public void off() {
    setBPM(0);
    sequencer.stop();
  };

  public void setBPM(int bpm) {
    this.bpm = bpm;
    sequencer.setTempoInBPM(getBPM());
    notifyBPMObservers();
  };

  public int getBPM() {
    return bpm;
  };

  public void registerObserver(BeatObserver o) {
    beatObservers.add(o);
  };

  public void removeObserver(BeatObserver o) {
    beatObservers.remove(o);
  };

  public void registerObserver(BPMObserver o) {
    bpmObservers.add(o);
  };

  public void removeObserver(BPMObserver o) {
    bpmObservers.remove(o);
  };

  public void beatEvent() {
    notifyBeatObservers();
  }

  public notifyBeatObservers(){
    for(BeatObserver: beatObserver : beatObservers){
      beatObserver.updateBeat();
    }
  }

  public notifyBPMObservers(){
    for(BPMObserver: bpmObserver : bpmObservers){
      bpmObserver.updateBeat();
    }
  }

  public void meta(MetaMessage message) {
    if (message.getType() == 47) {
      beatEvent();
      sequencer.start();
      setBPM(getBPM());
    }
  }

  public void setUpMidi() {
    try {
      sequencer = MidiSystem.getSequencer();
      sequencer.open();
      sequencer.addMetaEventListener(this);
      sequence = new Sequence(Sequence.PPQ, 4);
      track = sequence.createTrack();
      sequencer.setTempoInBPM(getBPM());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void buildTrackAddStart() {
    int[] trackList = { 35, 0, 46, 0 };
    sequence.deleteTrack(null);
    track = sequence.createTrack();

    makeTracks(trackList);
    track.add(makeEvent(192, 9, 1, 0, 4));
    try {
      sequencer.setSequence(sequence);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void makeTracks(int[] list) {
    for (int i = 0; i < list.length; i++) {
      int key = list[i];
      if (key != 0) {
        track.add(makeEvent(144, 9, key, 100, i));
        track.add(makeEvent(128, 9, key, 100, i + 1));
      }
    }
  }

  public MidiEvent makeEvent(int command, int channel, int one, int two, int tick) {
    MidiEvent event = null;
    try {
      ShortMessage a = new ShortMessage();
      a.setMessage(command, channel, one, two);
      event = new MidiEvent(a, tick);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return event;
  }
}