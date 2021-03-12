package de.electronicpeter.server.rest.layer.mapper;

import de.electronicpeter.perfect.permutation.Cycle;
import de.electronicpeter.perfect.permutation.Cycles;
import de.electronicpeter.perfect.permutation.Group;
import de.electronicpeter.perfect.permutation.MemoryStatistic;
import de.electronicpeter.server.rest.layer.generated.PerfectPermutationCycle;
import de.electronicpeter.server.rest.layer.generated.PerfectPermutationGroup;
import de.electronicpeter.server.rest.layer.generated.PerfectPermutationMetaInfo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(implementationPackage = "de.electronicpeter.server.rest.layer.mapper.generated")
public interface Service2RestMapper {
    PerfectPermutationMetaInfo mapMetaInfo(MemoryStatistic statistic);
    List<PerfectPermutationCycle> mapCycles(Cycles cycles);
    PerfectPermutationCycle mapCycle(Cycle cycles);
    List<PerfectPermutationGroup> mapGroups(List<Group> groups);
    PerfectPermutationGroup mapGroup(Group group);
}
