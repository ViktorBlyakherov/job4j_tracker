package ru.job4j.map;

import java.util.*;

public class AnalyzeByMap {
    public record Subject(String name, int score) {
    }

    public record Pupil(String name, List<Subject> subjects) {
    }

    public record Label(String name, double score) implements Comparable<Label> {
        @Override
        public int compareTo(Label o) {
            return Double.compare(this.score, o.score);
        }
    }

    public static double averageScore(List<Pupil> pupils) {
        double sum = 0;
        int index = 0;
        for (Pupil pupil : pupils) {
            for (Subject subject : pupil.subjects) {
                sum = sum + subject.score();
                index++;
            }
        }
        return index == 0 ? 0 : sum / index;
    }

    public static List<Label> averageScoreByPupil(List<Pupil> pupils) {
        List<Label> labels = new ArrayList<>();
        for (Pupil pupil : pupils) {
            double sum = 0;
            for (Subject subject : pupil.subjects()) {
                sum = sum + subject.score();
            }

            labels.add(new Label(pupil.name(), sum / pupil.subjects().size()));
        }
        return labels;
    }

    public static List<Label> averageScoreBySubject(List<Pupil> pupils) {
        List<Label> labels = new ArrayList<>();
        Map<String, Integer> map = new LinkedHashMap<>();
        for (Pupil pupil : pupils) {
            for (Subject subject : pupil.subjects()) {
                int temp = map.getOrDefault(subject.name(), 0);
                map.put(subject.name(), temp + subject.score());
            }
        }
        for (String subjectName : map.keySet()) {
            labels.add(new Label(subjectName, (double) map.get(subjectName) / pupils.size()));
        }
        return labels;
    }

    public static Label bestStudent(List<Pupil> pupils) {
        List<Label> labels = new ArrayList<>();
        for (Pupil pupil : pupils) {
            double sum = 0;
            for (Subject subject : pupil.subjects()) {
                sum = sum + subject.score();
            }
            labels.add(new Label(pupil.name(), sum));
        }
        labels.sort(Comparator.naturalOrder());
        return labels.get(labels.size() - 1);
    }

    public static Label bestSubject(List<Pupil> pupils) {
        List<Label> labels = new ArrayList<>();
        Map<String, Integer> map = new LinkedHashMap<>();
        for (Pupil pupil : pupils) {
            for (Subject subject : pupil.subjects()) {
                int temp = map.getOrDefault(subject.name(), 0);
                map.put(subject.name(), temp + subject.score());
            }
        }
        for (String subjectName : map.keySet()) {
            labels.add(new Label(subjectName, map.get(subjectName)));
        }
        labels.sort(Comparator.naturalOrder());
        return labels.get(labels.size() - 1);

    }
}
