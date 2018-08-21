package il.ac.bgu.cs.bp.bpjsrobot;

import il.ac.bgu.cs.bp.bpjs.eventselection.*;
import il.ac.bgu.cs.bp.bpjs.bprogram.runtimeengine.BSyncStatement;
import il.ac.bgu.cs.bp.bpjs.events.BEvent;
import il.ac.bgu.cs.bp.bpjs.eventsets.ComposableEventSet;
import il.ac.bgu.cs.bp.bpjs.eventsets.EventSet;
import il.ac.bgu.cs.bp.bpjs.eventsets.EventSets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.mozilla.javascript.Context;

public class RobocodeEventSelectionStrategy implements EventSelectionStrategy {
    private final Random rnd;
    private final long seed;

    public RobocodeEventSelectionStrategy(long seed) {
        this.rnd = new Random(seed);
        this.seed = seed;
    }

    public RobocodeEventSelectionStrategy() {
        this.rnd = new Random();
        this.seed = this.rnd.nextLong();
        this.rnd.setSeed(this.seed);
    }

    public Set<BEvent> selectableEvents(Set<BSyncStatement> statements, List<BEvent> externalEvents) {
        if (!externalEvents.isEmpty()){ //TODO: ugly patch - prettify going forward
            return Collections.singleton(externalEvents.get(0));
        }
        if (statements.isEmpty()) {
            return externalEvents.isEmpty() ? Collections.emptySet() : Collections.singleton(externalEvents.get(0));
        } else {
            EventSet blocked = ComposableEventSet.anyOf((Collection)statements.stream().filter((stmt) -> {
                return stmt != null;
            }).map(BSyncStatement::getBlock).filter((r) -> {
                return r != EventSets.none;
            }).collect(Collectors.toSet()));
            Set requested = (Set)statements.stream().filter((stmt) -> {
                return stmt != null;
            }).flatMap((stmt) -> {
                return stmt.getRequest().stream();
            }).collect(Collectors.toSet());

            Set var6;
            try {
                Context.enter();
                Set<BEvent> requestedAndNotBlocked = (Set)requested.stream().filter((req) -> {
                    return !blocked.contains((BEvent) req);
                }).collect(Collectors.toSet());
                var6 = requestedAndNotBlocked.isEmpty() ? (Set)externalEvents.stream().filter((e) -> {
                    return !blocked.contains(e);
                }).findFirst().map((e) -> {
                    return Collections.singleton(e);
                }).orElse(Collections.emptySet()) : requestedAndNotBlocked;
            } finally {
                Context.exit();
            }

            return var6;
        }
    }

    public Optional<EventSelectionResult> select(Set<BSyncStatement> statements, List<BEvent> externalEvents, Set<BEvent> selectableEvents) {
        if (selectableEvents.isEmpty()) {
            return Optional.empty();
        } else {
            BEvent chosen = (BEvent)(new ArrayList(selectableEvents)).get(this.rnd.nextInt(selectableEvents.size()));
            Set<BEvent> requested = (Set)statements.stream().filter((stmt) -> {
                return stmt != null;
            }).flatMap((stmt) -> {
                return stmt.getRequest().stream();
            }).collect(Collectors.toSet());
            return requested.contains(chosen) ? Optional.of(new EventSelectionResult(chosen)) : Optional.of(new EventSelectionResult(chosen, Collections.singleton(externalEvents.indexOf(chosen))));
        }
    }

    public long getSeed() {
        return this.seed;
    }
}
